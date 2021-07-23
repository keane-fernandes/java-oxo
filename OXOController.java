import OXOExceptions.*;

class OXOController
{
    OXOModel gameModel;
    private int rowNumber;
    private int colNumber;
    private int playerCurrent;
    private char letterCurrent;
    final public int ASCII_a = 97;
    final public int MAX_ROWS = 8;

    public OXOController(OXOModel model)
    {
        gameModel = model;
        playerCurrent = 0;
        gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(playerCurrent));
        letterCurrent = ' ';
        rowNumber = -1;
        colNumber = -1;
    }

    public void handleIncomingCommand(String command) throws OXOMoveException
    {
        if(gameModel.getWinner() == null) {

            char rowChar, colChar;
            RowOrColumn type;

            //----------------------------------------------------------
            // Invalid Length Identifier Exception
            //----------------------------------------------------------

            if (command.length() != 2) {
                throw new InvalidIdentifierLengthException(command.length());
            }

            //----------------------------------------------------------
            // Invalid Character Identifier Exception
            //----------------------------------------------------------

            // Checks for invalid row character
            rowChar = command.charAt(0);
            type = RowOrColumn.ROW;

            if (!Character.isLetter(rowChar)) {
                throw new InvalidIdentifierCharacterException(rowChar, type);
            }

            // Checks for invalid column character
            colChar = command.charAt(1);
            type = RowOrColumn.COLUMN;

            if (!Character.isDigit(colChar)) {
                throw new InvalidIdentifierCharacterException(colChar, type);
            }

            //----------------------------------------------------------
            // Outside Cell Range Exception
            //----------------------------------------------------------

            // Checks for row out of bounds
            type = RowOrColumn.ROW;

            if (Character.isUpperCase(rowChar)) {
                rowChar = Character.toLowerCase(rowChar);
            }

            rowNumber = (int) (rowChar - ASCII_a);

            if ((rowNumber < 0) || rowNumber >= gameModel.getNumberOfRows() || (rowNumber > MAX_ROWS) ) {
                throw new OutsideCellRangeException(rowNumber+1, type);
            }

            // Checks for column out of bounds
            type = RowOrColumn.COLUMN;
            colNumber = Character.getNumericValue(colChar) - 1;

            if ((colNumber < 0) || (colNumber >= gameModel.getNumberOfColumns()) ) {
                throw new OutsideCellRangeException(colNumber+1, type);
            }

            //----------------------------------------------------------
            // Cell Already Taken Exception
            //----------------------------------------------------------

            if (gameModel.getCellOwner(rowNumber, colNumber) != null) {
                throw new CellAlreadyTakenException(rowNumber+1, colNumber+1);
            }

            //----------------------------------------------------------
            // Set OXOModel Cell Owner
            //----------------------------------------------------------

            gameModel.setCellOwner(rowNumber, colNumber, gameModel.getCurrentPlayer());
            letterCurrent = gameModel.getPlayerByNumber(playerCurrent).getPlayingLetter();

            //----------------------------------------------------------
            // Win detection
            //----------------------------------------------------------

            if (checkForWin()) {
                gameModel.setWinner(gameModel.getCurrentPlayer());
            }

            //----------------------------------------------------------
            // Draw detection
            //----------------------------------------------------------
            if (checkForDraw()) {
                gameModel.setGameDrawn();
            }

            // Increments current player and sets current player to the next one
            playerCurrent++;
            if (playerCurrent >= gameModel.getNumberOfPlayers()) {
                playerCurrent = 0;
            }
            gameModel.setCurrentPlayer(gameModel.getPlayerByNumber(playerCurrent));
        }
    }

    public boolean checkForWin()
    {
        if ( winHorizontal() || winVertical() || winDiagonal() ){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean winHorizontal()
    {
        int iterator_column;
        int counter = 1;

        iterator_column = colNumber - 1;
        while( iterator_column >= 0 ){
            if ((gameModel.getCellOwner(rowNumber, iterator_column) == null) ||
                    (gameModel.getCellOwner(rowNumber, iterator_column).getPlayingLetter() != letterCurrent)){
                break;
            }
            counter++;
            iterator_column--;
        }

        iterator_column = colNumber + 1;
        while( iterator_column < gameModel.getNumberOfColumns() ){
            if ((gameModel.getCellOwner(rowNumber, iterator_column) == null) ||
                    (gameModel.getCellOwner(rowNumber, iterator_column).getPlayingLetter() != letterCurrent)){
                break;
            }
            counter++;
            iterator_column++;
        }

        if (counter >= gameModel.getWinThreshold()){
            return true;
        }
        return false;
    }

    public boolean winVertical()
    {
        int iterator_row;
        int counter = 1;

        iterator_row = rowNumber - 1;
        while( iterator_row >= 0 ){
            if ((gameModel.getCellOwner(iterator_row, colNumber) == null) ||
                    (gameModel.getCellOwner(iterator_row, colNumber).getPlayingLetter() != letterCurrent)){
                break;
            }
            counter++;
            iterator_row--;
        }

        iterator_row = rowNumber + 1;
        while( iterator_row < gameModel.getNumberOfRows() ){
            if ((gameModel.getCellOwner(iterator_row, colNumber) == null) ||
                    (gameModel.getCellOwner(iterator_row, colNumber).getPlayingLetter() != letterCurrent)){
                break;
            }
            counter++;
            iterator_row++;
        }

        if (counter >= gameModel.getWinThreshold()){
            return true;
        }
        return false;
    }

    public boolean winDiagonal()
    {
        int iterator_row;
        int iterator_column;
        int counter;

        //----------------------------------------------------------
        // Counts the number of contiguous cells from the
        // top-left direction to the bottom-right direction
        //----------------------------------------------------------

        iterator_row = rowNumber - 1;
        iterator_column = colNumber - 1;
        counter = 1;

        while( (iterator_row >= 0) && (iterator_column >= 0) ){
            if ((gameModel.getCellOwner(iterator_row, iterator_column) == null) ||
                    (gameModel.getCellOwner(iterator_row, iterator_column).getPlayingLetter() != letterCurrent)) {
                break;
            }
            iterator_row--;
            iterator_column--;
            counter++;
        }

        iterator_row = rowNumber + 1;
        iterator_column = colNumber + 1;

        while( (iterator_row < gameModel.getNumberOfRows()) && (iterator_column < gameModel.getNumberOfColumns()) ){
            if ( (gameModel.getCellOwner(iterator_row, iterator_column) == null) ||
                    (gameModel.getCellOwner(iterator_row, iterator_column).getPlayingLetter() != letterCurrent) ) {
                break;
            }
            iterator_row++;
            iterator_column++;
            counter++;
        }

        if(counter >= gameModel.getWinThreshold()){
            return true;
        }

        //----------------------------------------------------------
        // Counts the number of contiguous cells from the top-right
        // direction to the bottom left direction
        //----------------------------------------------------------
        iterator_row = rowNumber + 1;
        iterator_column = colNumber - 1;
        counter = 1;

        while( (iterator_row < gameModel.getNumberOfRows()) && (iterator_column >= 0) ){
            if ( (gameModel.getCellOwner(iterator_row, iterator_column) == null) ||
                    (gameModel.getCellOwner(iterator_row, iterator_column).getPlayingLetter() != letterCurrent)) {
                break;
            }
            iterator_row++;
            iterator_column--;
            counter++;
        }

        iterator_row = rowNumber - 1;
        iterator_column = colNumber + 1;

        while( (iterator_row >= 0) && (iterator_column < gameModel.getNumberOfColumns()) ){
            if ( (gameModel.getCellOwner(iterator_row, iterator_column) == null) ||
                    (gameModel.getCellOwner(iterator_row, iterator_column).getPlayingLetter() != letterCurrent)) {
                break;
            }
            iterator_row--;
            iterator_column++;
            counter++;
        }

        if(counter >= gameModel.getWinThreshold()){
            return true;
        }

        return false;
    }

    public boolean checkForDraw()
    {
        int j, i;

        for (j = 0; j < gameModel.getNumberOfRows(); j++){
            for (i = 0; i < gameModel.getNumberOfColumns(); i++){
                if (gameModel.getCellOwner(j, i) == null){
                    return false;
                }
            }
        }
        return true;
    }
}
