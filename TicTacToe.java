import util.TicTacToeUtil;

import java.util.*;

public class TicTacToe {

    /*
     * The Below two ArrayList contains the list of Positions Played by the Player and CPU respectively.
     * Making it static and declaring it global  makes sure that all methods can have access to it.
     */
    private static final List<List<Integer>> winning = new ArrayList<>();
    private static final ArrayList<Integer> cpuPositions = new ArrayList<>();
    private static final ArrayList<Integer> playerPositions1 = new ArrayList<>();
    private static final ArrayList<Integer> playerPositions2 = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!!");
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        TicTacToeUtil.printGameBoard(gameBoard);
        Scanner scan = new Scanner(System.in);

        Thread thread = new Thread(TicTacToe::buildWinnerList);
        thread.start();


        System.out.println("Hey, are you going to play with  a Friend?");
        System.out.println("If yes type (Y/y)");

        String opponent = scan.next();


        while (true) {
            //Player1's Snippet
            System.out.println("Enter the Position b/w(1-9)::PLAYER 1::");
            int playerPosition = scan.nextInt();
            while (playerPosition < 0 || playerPosition > 10) {
                System.out.println("Please Enter b/w (1-9)");
                playerPosition = scan.nextInt();
            }
            while (playerPositions1.contains(playerPosition) ||
                    playerPositions2.contains(playerPosition) ||
                    cpuPositions.contains(playerPosition)
            ) {
                System.out.println("Position Taken!!, Enter a different Position");
                playerPosition = scan.nextInt();
            }
            placePiece(gameBoard, playerPosition, "Player1");
            TicTacToeUtil.printGameBoard(gameBoard);

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            //Player2'sSnippet
            if (opponent.equals("y") || opponent.equals("Y")) {
                System.out.println("Enter the Position b/w(1-9)::PLAYER 2::");
                int playerPosition2 = scan.nextInt();
                while (playerPosition2 < 0 || playerPosition2 > 10) {
                    System.out.println("Please Enter b/w (1-9)");
                    playerPosition2 = scan.nextInt();
                }
                while (playerPositions1.contains(playerPosition2) || playerPositions2.contains(playerPosition2)) {
                    System.out.println("Position Taken!!, Enter a different Position");
                    playerPosition2 = scan.nextInt();
                }
                placePiece(gameBoard, playerPosition2, "Player2");
            } else {
                //CPU's Snippet!
                Random rand = new Random();
                int cpuPosition = rand.nextInt(9) + 1;
                while (playerPositions1.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                    cpuPosition = rand.nextInt(9) + 1;
                }
                placePiece(gameBoard, cpuPosition, "CPU");
            }
            TicTacToeUtil.printGameBoard(gameBoard);
            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    private static void buildWinnerList() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> cross1 = Arrays.asList(1, 5, 9);
        List<Integer> cross2 = Arrays.asList(3, 5, 7);


        winning.add(topRow);
        winning.add(botRow);
        winning.add(midRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

    }

    private static String checkWinner() {

        for (List<Integer> l : winning) {
            if (playerPositions1.containsAll(l)) {
                return "Congratulations Player1 Won!!";
            } else if (playerPositions2.containsAll(l)) {
                return "Congratulations Player2 Won!!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU Wins!!  :( !";
            } else if (playerPositions1.size() + playerPositions2.size() + cpuPositions.size() == 9) {
                return "Hey,it's 'TIE' ";
            }
        }
        return "";
    }

    private static void placePiece(char[][] gameBoard, int position, String user) {
        //Places the Symbol('X'/'O') in the desired Position.
        char symbol = ' ';
        switch (user) {
            case "Player1":
                symbol = 'X';
                playerPositions1.add(position);
                break;
            case "Player2":
                symbol = 'O';
                playerPositions2.add(position);
                break;
            case "CPU":
                symbol = 'O';
                cpuPositions.add(position);
                break;
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }
}

