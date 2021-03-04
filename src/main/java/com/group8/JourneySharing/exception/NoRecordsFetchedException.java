package com.group8.JourneySharing.exception;

public class NoRecordsFetchedException extends FatalException
{
    public NoRecordsFetchedException()
    {}


    public NoRecordsFetchedException( String message )
    {
        super( "No records found", message );
    }


    public NoRecordsFetchedException( String message, Throwable cause )
    {
        super( message, cause );
    }
}
