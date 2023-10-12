import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    public void testPrefixEvaluation() {

        // Define test cases
        String[] testCase1 = { "+", "1", "2" };
        String[] testCase2 = { "*", "+", "1", "2", "3" };
        String[] testCase3 = { "/", "1", "0"};

        try {
            //float result3 = Main.prefixEvaluation(testCase3, new StackList(testCase3.length));
            // Assert the expected results
            assertEquals(3.0, Main.prefixEvaluation(testCase1, new StackList(testCase1.length)), 0.01);
            assertEquals(9.0, Main.prefixEvaluation(testCase2, new StackList(testCase2.length)), 0.01);
            assertThrows(Exception.class,() -> {Main.prefixEvaluation(testCase3 , new StackList(testCase3.length));});
            //assertEquals(, result3, 0.01);

        }
        catch (Exception e)
        {
            // Handle any exceptions if the prefixEvaluation method throws them
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void postfixEvaluation()
    {
        // Define test cases
        String[] testCase1 = {"2", "3", "5", "*", "+"}; //17
        String[] testCase2 = {"1", "2", "+", "3", "*"}; //9
        String[] testCase3 = {"2", "0", "/"}; // Division by zero test case

        try
        {
            // Assert the expected results
            assertEquals(17.0, Main.postfixEvaluation(testCase1, new StackList(testCase1.length)), 0.01);
            assertEquals(9.0, Main.postfixEvaluation(testCase2, new StackList(testCase2.length)), 0.01);
            assertThrows(Exception.class, () -> Main.postfixEvaluation(testCase3, new StackList(testCase3.length)));
        }
        catch (Exception e)
        {
            // Handle any exceptions if the prefixEvaluation method throws them
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void testInfixToPostfix() {
        // Define test cases
        String[] infixExpression1 = { "2", "+", "3" };
        String[] infixExpression2 = { "(", "1", "+", "2", ")", "*", "3" };
        String[] infixExpression3 = { "1", "+", "2", "*", "3" };
        String[] infixExpression4 = { "1", "+", "2", "*", "3", "+", "4" };

        try {
            // Test the infixToPostfix method with the test cases
            String postfixResult1 = Main.infixToPostfix(infixExpression1, new StackList(infixExpression1.length));
            String postfixResult2 = Main.infixToPostfix(infixExpression2, new StackList(infixExpression2.length));
            String postfixResult3 = Main.infixToPostfix(infixExpression3, new StackList(infixExpression3.length));
            String postfixResult4 = Main.infixToPostfix(infixExpression4, new StackList(infixExpression3.length));

            // Assert the expected results
            assertEquals("2 3 +", postfixResult1);
            assertEquals("1 2 + 3 *", postfixResult2);
            assertEquals("1 2 3 * +", postfixResult3);
            assertEquals("1 2 3 * + 4 +", postfixResult4); //not returning properly //////////////////////////////////

        }
        catch (Exception e)
        {
            // Handle any exceptions if the infixToPostfix method throws them
            e.printStackTrace();
        }
    }
}