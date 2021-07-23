package OXOExceptions;

public class CellAlreadyTakenException extends OXOMoveException
{
    public CellAlreadyTakenException()
    {
        super();
    }

    public CellAlreadyTakenException(int rowNumber, int columnNumber)
    {
        super(rowNumber, columnNumber);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Row " + super.getRow() + " Column " + super.getColumn() + " has already been taken.";
    }
}
