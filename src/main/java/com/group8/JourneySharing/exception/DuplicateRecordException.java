package com.group8.JourneySharing.exception;

public class DuplicateRecordException extends FatalException
{
    public DuplicateRecordException()
    {
        super();
    }


    public DuplicateRecordException( String message )
    {
        super( "Duplicate record", message );
    }


    public DuplicateRecordException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
