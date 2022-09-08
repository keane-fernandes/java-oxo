package OXOExceptions;

public class InvalidIdentifierCharacterException extends InvalidIdentifierException
{
    char character;
    RowOrColumn type;

    public InvalidIdentifierCharacterException(char character, RowOrColumn type)
    {
        super();
        this.character = character;
        this.type = type;
    }

    @Override
    public String toString() {
        if(type == RowOrColumn.ROW){
            return super.toString() + character + " is not a valid row index.";
        }
        else if (type == RowOrColumn.COLUMN){
            return super.toString() + character + " is not a valid column index.";
        }
        else{
            return super.toString() + "Some character outside of the ASCII range detected.";
        }
    }
}
