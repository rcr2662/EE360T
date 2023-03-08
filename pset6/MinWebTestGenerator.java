package pset6;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MinWebTestGenerator {
    public static final int NUMBER_OF_TESTS = 128;
    public static final int INPUT_TYPES = 4;
    public static final int ZERO = 0;
    public static final String NOT_AN_INTEGER = "Not an integer.";

    Random random = new Random(12345678);
    ArrayList<Combination> combinations = new ArrayList<Combination>();

    public static void main(String[] a) {
        String suite = new MinWebTestGenerator().createTestSuite();
        System.out.println(suite);
    }
	
    String createTestSuite() {
        StringBuilder sb = new StringBuilder();
        sb.append(packageDecl());
        sb.append("\n");
        sb.append(imports());
        sb.append("\n");
        sb.append(testsuite());
        
        return sb.toString();
    }

    String packageDecl() {
        return "package pset6;\n";
    }

    String imports() {
        return "import java.io.IOException;\n" 
        + "import static org.junit.Assert.*;\n\n"
        + "import org.junit.BeforeClass;\n"
        + "import org.junit.AfterClass;\n"
        + "import org.junit.Test;\n\n"
        + "import org.openqa.selenium.By;\n"
        + "import org.openqa.selenium.WebDriver;\n"
        + "import org.openqa.selenium.WebElement;\n"
        + "import org.openqa.selenium.firefox.FirefoxDriver;\n";
    }

	  String testsuite() {
        StringBuilder sb = new StringBuilder();
        sb.append("public class MinWebTestSuite {\n");
            sb.append(openBrowser());
            sb.append(addTests());
            sb.append(closeBrowser()); 
            sb.append("}\n");
        return sb.toString();
    }

    String addTests() {
        StringBuilder sb = new StringBuilder();
        
        createCombinations(new Object[INPUT_TYPES],
                           new Integer[]{INPUT_TYPES - 1, INPUT_TYPES - 1, INPUT_TYPES - 1, 1},
                           0);                

        for (int test = 0; test < NUMBER_OF_TESTS; test += 1) { 
            Combination possibility = combinations.get(test); 
            
            generateInputValues(possibility);
            
            Object x = possibility.combination[0];
            Object y = possibility.combination[1];
            Object z = possibility.combination[2];            

            sb.append(tab(1) + "@Test\n");
            sb.append(tab(1) + "public void t" + Integer.toString(test) + "() {\n");
           
            sb.append(tab(2) + "String currentWorkingDirectory = System.getProperty(\"user.dir\");\n");

            sb.append(tab(2) + "String minPath = \"file://\"" 
                             + "+ currentWorkingDirectory + \"/min.html\";\n"); 

            sb.append(tab(2) + "driver.get(minPath);\n"); 
            
            sb.append(tab(2) + "WebElement element = driver.findElement(By.id(\"x\"));\n");
            sb.append(tab(2) + "element.sendKeys(\"" 
                             + stringRepresentation(x)
                             + "\");\n");
            
            sb.append(tab(2) + "element = driver.findElement(By.id(\"y\"));\n");
            sb.append(tab(2) + "element.sendKeys(\""
                             + stringRepresentation(y)
                             + "\");\n");

            sb.append(tab(2) + "element = driver.findElement(By.id(\"z\"));\n");
            sb.append(tab(2) + "element.sendKeys(\""
                             + stringRepresentation(z)
                             + "\");\n");
           
            sb.append(tab(2) + "WebElement result = driver.findElement(By.id(\"result\"));\n");

            if ((Integer) possibility.combination[3] == 1) {
                sb.append(tab(2) + "element = driver.findElement(By.id(\"computeButton\"));\n");
                sb.append(tab(2) + "element.click();\n");
            }

            sb.append(tab(2) + "String output = result.getText();\n");

            if ((Integer) possibility.combination[3] == 1) {
                boolean illegalInput = x instanceof String || y instanceof String || z instanceof String;
                String result = "";

                if (illegalInput) {
                    result = "Please enter integer values only!";

                    sb.append(tab(2) + "assertEquals(\"" + result + "\", output);\n");
                }
                else {
                    int minimum = Math.min(Math.min((Integer) x, (Integer) y), (Integer) z);
                    result = Integer.toString(minimum);
                
                    sb.append(tab(2) + "assertEquals(\"min("
                                     + stringRepresentation(x) + ", "
                                     + stringRepresentation(y) + ", "
                                     + stringRepresentation(z) + ") = " 
                                     + result   
                                     + "\", output);\n");
                }

            }

            sb.append(tab(1) + "}\n\n");
        } 
        
        return sb.toString();            
    }
    
    /**
     * Opens the web browser before all test cases are executed.
     */
    String openBrowser() {
        StringBuilder sb = new StringBuilder();
        sb.append(tab(1) + "static WebDriver driver;\n\n");
        sb.append(tab(1) + "@BeforeClass\n");
        sb.append(tab(1) + "public static void setUp() {\n");
        sb.append(tab(2) + "driver = new FirefoxDriver();\n");
         
        sb.append(tab(1) + "}\n\n");
        return sb.toString();
    }

    /**
     * Closes the web browser after all test cases are executed.
     */ 
    String closeBrowser() {
        StringBuilder sb = new StringBuilder();
        sb.append(tab(1) + "@AfterClass\n");
        sb.append(tab(1) + "public static void tearDown() throws IOException {\n");
        sb.append(tab(2) + "driver.quit();\n");
        sb.append(tab(2) + "driver = null;\n");
        sb.append(tab(1) + "}\n");
        return sb.toString();
    }

    /**
     * Returns a 4-space tab.
     * @param tabCount : The number of 4-space tabs to return.
     */ 
    String tab(int tabCount) {
        StringBuilder sb = new StringBuilder();
        for (int tab = 0; tab < tabCount; tab += 1) {
            sb.append("    ");
        }

        return sb.toString();        
    }

    /**
     * Creates all combinations for combinatorial coverage, but using
     * representational Integer values (range(0, 4)).
     * @param valueIDs : The array holding the combination.
     * @param rangesForEachIndex : The array holding the capacity of possible
     * input values for each input ([2, 3] := first input has 2 possible input
     * types, second input has 3 possible input types).
     * @param currentIndex : A pointer to the index of valueIDs.
     */
    void createCombinations(Object[] valueIDs, Integer[] rangesForEachIndex, Integer currentIndex) {
        if (currentIndex == valueIDs.length) {
            Object[] combination = Arrays.copyOf(valueIDs, valueIDs.length);
            combinations.add(new Combination(false, combination));
            return;
        }
       
        for (int index = 0; index <= rangesForEachIndex[currentIndex]; index += 1) {
            valueIDs[currentIndex] = (Object) index;
            createCombinations(valueIDs, rangesForEachIndex, currentIndex + 1);
        } 
    }
  
    /**
     * Converts the combination possibility from its ID representation to the
     * actual test case inputs.
     * @param possibility: The Combination object containing the
     * representational combination possibility array.
     */ 
    void generateInputValues(Combination possibility) {
        for (int value = 0; value < possibility.combination.length - 1; value += 1) {
            possibility.combination[value] = getValueMapping((Integer) possibility.combination[value]);
        }
    }
   
    /**
     * Generates the appropiate value from the ID.
     * 1 : Any integer greater than 0.
     * 2 : Any integer less than 0.
     * 3 : The string literal "String."
     * 0 (Default) : The value 0.
     * @param ID : The integer value representing one of this method's return
     * values.
     */ 
    Object getValueMapping(int ID) {
        switch (ID) {
            case 1:
                return Integer.valueOf(random.nextInt(Integer.MAX_VALUE - 1 + 1) + 1);
            case 2:
                return Integer.valueOf(-1 * (random.nextInt(Integer.MAX_VALUE - 1 + 1) + 1));
            case 3:
                return "String";                    
            default:
                return Integer.valueOf(0);
        }
    }

    /**
     * Returns the string representation of the "value" object.
     * @param value : The object to return a string representation of. "value"
     * must either be an Integer or String.
     */ 
    String stringRepresentation(Object value) {
        if (value instanceof Integer) {
            return Integer.toString((Integer) value);
        }
        else if (value instanceof String) {
            return (String) value;
        }
        throw new IllegalArgumentException("Invalid reference type.");
    }
    
    public class Combination {
        boolean generated;
        Object[] combination;

        public Combination(boolean generated, Object[] combination) {
            this.generated = generated;
            this.combination = combination;
        }
    }
}