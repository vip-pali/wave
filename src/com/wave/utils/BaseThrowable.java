package com.wave.utils;

/**
* This interface represents the interface common to BaseException nand
* BaseRuntimeException classes.
*/
public interface BaseThrowable
{
	/**
	* Does this exception wrap another exception?
	*/
	public boolean isAWrapper();

	/**
	* Does this exception wrap another Base Throwableexception?
	*/
	public boolean isABaseThrowableWrapper();

	/**
	* Get the wrapped exception if any, else get null.
	*/
	public Throwable getWrappedException();

	/**
	* Get The excepion data associated with the exception.
	*/
	public BaseExceptionData getBaseExceptionData();

	
	/**
	* Returns the message of this exception. All parameter specialization of the
	* message template is done at this stage.
	*/
	public String getMessage();
}
