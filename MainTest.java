import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    public void testPrefixEvaluation() {

        // Define test cases
        String[] testCase1 = { "+", "1", "2" };
        String[] testCase2 = { "*", "+", "1", "2", "3" };
        String[] testCase3 = { "/", "1", "0"};
        String[] testCase4 = { "*", "2", "3" };
        String[] testCase5 = { "/", "6", "2" };
        String[] testCase6 = { "+", "*", "2", "3", "4" };
        String[] testCase7 = { "-", "+", "2", "3", "4" };
        String[] testCase8 = { "*", "/", "6", "2", "3" };
        String[] testCase9 = { "+", "-", "2", "3", "4" };
        String[] testCase10 = { "-", "*", "2", "3", "/", "6", "2" };

        try {
            
            
            assertEquals(3.0, Main.prefixEvaluation(testCase1, new StackList(testCase1.length)), 0.01);
            assertEquals(9.0, Main.prefixEvaluation(testCase2, new StackList(testCase2.length)), 0.01);
            assertThrows(Exception.class,() -> {Main.prefixEvaluation(testCase3 , new StackList(testCase3.length));});
            assertEquals(6.0, Main.prefixEvaluation(testCase4, new StackList(testCase4.length)), 0.01);
            assertEquals(3.0, Main.prefixEvaluation(testCase5, new StackList(testCase5.length)), 0.01);
            assertEquals(10.0, Main.prefixEvaluation(testCase6, new StackList(testCase6.length)), 0.01);
            assertEquals(1.0, Main.prefixEvaluation(testCase7, new StackList(testCase7.length)), 0.01);
            assertEquals(9.0, Main.prefixEvaluation(testCase8, new StackList(testCase8.length)), 0.01);
            assertEquals(3.0, Main.prefixEvaluation(testCase9, new StackList(testCase9.length)), 0.01);
            assertEquals(3.0, Main.prefixEvaluation(testCase10, new StackList(testCase10.length)), 0.01);

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
        String[] testCase4 = {"4", "2", "5", "*", "+"};
        String[] testCase5 = {"3", "4", "+", "2", "*"};
        String[] testCase6 = {"6", "0", "/"};
        String[] testCase7 = {"7", "2", "3", "*", "+"};
        String[] testCase8 = {"8", "2", "+", "3", "/"};
        String[] testCase9 = {"5", "3", "4", "*", "+"};
        String[] testCase10 = {"6", "7", "+", "2", "*"};
        String[] testCase11 = {"8", "0", "/"};
        String[] testCase12 = {"9", "2", "3", "*", "+"};
        String[] testCase13 = {"10", "2", "+", "0", "/"};
        String[] testCase14 = {};
        String[] testCase15 = {"1"};


        try
        {
            // Assert the expected results
            assertEquals(17.0, Main.postfixEvaluation(testCase1, new StackList(testCase1.length)), 0.01);
            assertEquals(9.0, Main.postfixEvaluation(testCase2, new StackList(testCase2.length)), 0.01);
            assertThrows(Exception.class, () -> Main.postfixEvaluation(testCase3, new StackList(testCase3.length)));
            assertEquals(14.0, Main.postfixEvaluation(testCase4, new StackList(testCase4.length)), 0.01);
            assertEquals(14.0, Main.postfixEvaluation(testCase5, new StackList(testCase5.length)), 0.01);
            assertThrows(Exception.class, () -> Main.postfixEvaluation(testCase6, new StackList(testCase6.length)));
            assertEquals(13.0, Main.postfixEvaluation(testCase7, new StackList(testCase7.length)), 0.01);
            assertEquals(3.0, Main.postfixEvaluation(testCase8, new StackList(testCase8.length)), 0.01);
            assertEquals(17.0, Main.postfixEvaluation(testCase9, new StackList(testCase9.length)), 0.01);
            assertEquals(26.0, Main.postfixEvaluation(testCase10, new StackList(testCase10.length)), 0.01);
            assertThrows(Exception.class, () -> Main.postfixEvaluation(testCase11, new StackList(testCase11.length)));
            assertEquals(15.0, Main.postfixEvaluation(testCase12, new StackList(testCase12.length)), 0.01);
            assertThrows(Exception.class, () -> Main.postfixEvaluation(testCase13, new StackList(testCase13.length)));
            assertThrows(Exception.class, () -> Main.postfixEvaluation(testCase14, new StackList(testCase14.length)));
            assertEquals(1.0, Main.postfixEvaluation(testCase15, new StackList(testCase15.length)), 0.01);

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
        String[] infixExpression5 = { "5", "+", "3", "*", "2" };
        String[] infixExpression6 = { "8", "-", "4", "/", "2" };
        String[] infixExpression7 = { "7", "+", "9", "*", "3", "-", "6" };
        String[] infixExpression8 = { "2", "*", "3", "+", "4", "*", "5" };
        String[] infixExpression9 = { "1", "+", "2", "*", "3", "-", "4", "/", "2" };
        String[] infixExpression10 = { "6", "*", "2", "/", "3" };
        String[] infixExpression11 = { "4", "+", "8", "*", "2", "-", "1" };
        String[] infixExpression12 = { "3", "*", "7", "+", "5", "/", "2" };
        String[] infixExpression13 = { "9", "-", "1", "*", "3", "+", "6" };
        String[] infixExpression14 = { "2", "+", "3", "*", "4", "-", "5", "/", "1" };
        String[] infixExpression15 = { "7", "*", "2", "+", "3" };
        String[] infixExpression16 = { "5", "+", "9", "*", "2", "-", "4" };
        String[] infixExpression17 = { "4", "*", "8", "+", "6", "/", "3" };
        String[] infixExpression18 = { "10", "-", "2", "*", "3", "+", "7" };
        String[] infixExpression19 = { "3", "+", "4", "*", "5", "-", "6", "/", "2" };
        String[] infixExpression20 = { "6", "+", "7", "*", "2", "-", "3", "/", "1" };

        try {
            // Test the infixToPostfix method with the test cases
            String postfixResult1 = Main.infixToPostfix(infixExpression1, new StackList(infixExpression1.length));
            String postfixResult2 = Main.infixToPostfix(infixExpression2, new StackList(infixExpression2.length));
            String postfixResult3 = Main.infixToPostfix(infixExpression3, new StackList(infixExpression3.length));
            String postfixResult4 = Main.infixToPostfix(infixExpression4, new StackList(infixExpression4.length));
            String postfixResult5 = Main.infixToPostfix(infixExpression5, new StackList(infixExpression5.length));
            String postfixResult6 = Main.infixToPostfix(infixExpression6, new StackList(infixExpression6.length));
            String postfixResult7 = Main.infixToPostfix(infixExpression7, new StackList(infixExpression7.length));
            String postfixResult8 = Main.infixToPostfix(infixExpression8, new StackList(infixExpression8.length));
            String postfixResult9 = Main.infixToPostfix(infixExpression9, new StackList(infixExpression9.length));
            String postfixResult10 = Main.infixToPostfix(infixExpression10, new StackList(infixExpression10.length));
            String postfixResult11 = Main.infixToPostfix(infixExpression11, new StackList(infixExpression11.length));
            String postfixResult12 = Main.infixToPostfix(infixExpression12, new StackList(infixExpression12.length));
            String postfixResult13 = Main.infixToPostfix(infixExpression13, new StackList(infixExpression13.length));
            String postfixResult14 = Main.infixToPostfix(infixExpression14, new StackList(infixExpression14.length));
            String postfixResult15 = Main.infixToPostfix(infixExpression15, new StackList(infixExpression15.length));
            String postfixResult16 = Main.infixToPostfix(infixExpression16, new StackList(infixExpression16.length));
            String postfixResult17 = Main.infixToPostfix(infixExpression17, new StackList(infixExpression17.length));
            String postfixResult18 = Main.infixToPostfix(infixExpression18, new StackList(infixExpression18.length));
            String postfixResult19 = Main.infixToPostfix(infixExpression19, new StackList(infixExpression19.length));
            String postfixResult20 = Main.infixToPostfix(infixExpression20, new StackList(infixExpression20.length));


            // Assert the expected results
            assertEquals("2 3 +", postfixResult1);
            assertEquals("1 2 + 3 *", postfixResult2);
            assertEquals("1 2 3 * +", postfixResult3);
            assertEquals("1 2 3 * + 4 +", postfixResult4);
            assertEquals("5 3 2 * +", postfixResult5);
            assertEquals("8 4 2 / -", postfixResult6);
            assertEquals("7 9 3 * + 6 -", postfixResult7);
            assertEquals("2 3 * 4 5 * +", postfixResult8);
            assertEquals("1 2 3 * + 4 2 / -", postfixResult9);
            assertEquals("6 2 * 3 /", postfixResult10);
            assertEquals("4 8 2 * + 1 -", postfixResult11);
            assertEquals("3 7 * 5 2 / +", postfixResult12);
            assertEquals("9 1 3 * - 6 +", postfixResult13);
            assertEquals("2 3 4 * + 5 1 / -", postfixResult14);
            assertEquals("7 2 * 3 +", postfixResult15);
            assertEquals("5 9 2 * + 4 -", postfixResult16);
            assertEquals("4 8 * 6 3 / +", postfixResult17);
            assertEquals("10 2 3 * - 7 +", postfixResult18);
            assertEquals("3 4 5 * + 6 2 / -", postfixResult19);
            assertEquals("6 7 2 * + 3 1 / -", postfixResult20);


        }
        catch (Exception e)
        {
            // Handle any exceptions if the infixToPostfix method throws them
            e.printStackTrace();
        }
    }
}