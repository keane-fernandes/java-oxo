package OXOExceptions;

public class OXOMoveException extends Exception
{
    protected int rowNumber;
    protected int columnNumber;

    public OXOMoveException()
    {
        this.rowNumber = -1;
        this.columnNumber = -1;
    }

    public OXOMoveException(int row, int column)
    {
        this.rowNumber = row;
        this.columnNumber = column;
    }
    
    protected int getRow()
    {
        return rowNumber;
    }

    protected int getColumn()
    {
        return columnNumber;
    }

    @Override
    public String toString() {
        return "OXO Move Exception at Row " + rowNumber + " Column " + columnNumber + " -> ";
    }
}
