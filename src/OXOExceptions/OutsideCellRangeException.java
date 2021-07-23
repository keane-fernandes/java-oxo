package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException
{
    private int position;
    private RowOrColumn type;

    public OutsideCellRangeException(int position, RowOrColumn type)
    {
        super();
        this.position = position;
        this.type = type;
    }

    @Override
    public String toString() {
        if(type == RowOrColumn.ROW){
            return super.toString() + "The row number " + position + " is outside range.";
        }
        else if (type == RowOrColumn.COLUMN){
            return super.toString() + "The column number " + position + " is outside range";
        }
        else {
            return super.toString() + "The cell index at position " + position + " is outside range.";
        }
    }
}
