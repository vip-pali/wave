package com.wave.utils;


import java.io.PrintWriter;
import java.io.PrintStream;


/**
* This class represents the base class of exceptions to be thrown. As the base
* class it supports the following idioms of exception throwing - 
* <ul>
* 	<li>Wrapping an underlying exception - <em>without</em> any semantic wrapping (like
* 	wrapping the message with additional information like &quot;could not create
* 	foo:&lt;sql message&gt;&quot;.</li>
* </ul>
*/
public class BaseRuntimeException
	extends RuntimeException
	implements BaseThrowable, java.io.Serializable
{
	/**
	* @see #BaseRuntimeException(Throwable,String,String[]).
	*/
	public BaseRuntimeException(Throwable toWrap)
	{
		this(toWrap,null);
	}
	
	/**
	* Creates a BaseRuntimeException object wrapping the specified exception to be
	* wrapped. Nothing is wrapped if <em>toWrap</em> is null. See parameter
	* documentation for various possibilities.
	* @param toWrap the exception to be wrapped. Nothing is wrapped if null is
	* passed.
	* @param message the message
	*/
	public BaseRuntimeException(Throwable toWrap,String message)
	{
		_exceptionData = new BaseExceptionData(toWrap,message);
		
		if(toWrap instanceof StackOverflowError)
		{
			toWrap.printStackTrace();
			throw new BaseRuntimeException("StackOverflowError");
		}

	}


	/**
	* @see #BaseRuntimeException(Throwable,String,String[]).
	*/
	public BaseRuntimeException(String message)
	{
		this(null,message);
	}

	/**
	* Does this exception wrap another exception?
	*/
	public boolean isAWrapper()
	{
		return _exceptionData.isAWrapper();
	}
	
	public boolean isABaseThrowableWrapper()
	{
		return _exceptionData.isABaseThrowableWrapper();
	}

	/**
	* Get the wrapped exception if any, else get null.
	*/
	public Throwable getWrappedException()
	{
		return _exceptionData.getWrappedException();
	}
	
	/**
	*/
	public String getMessage()
	{
		return _exceptionData.getMessage();
	}

	public BaseExceptionData getBaseExceptionData()
	{
		return _exceptionData;
	}

	public Throwable getInnermostException()
	{
		Throwable ex = _exceptionData.getInnermostException();

		if(ex == null)
		{
			return this;
		}
		else
		{
			return ex;
		}
	}

	public StackTraceElement[] getStackTrace()
	{
		Throwable ex = getInnermostException();
		
		if(ex == this)
		{
			return super.getStackTrace();
		}
		else
		{
			return ex.getStackTrace();
		}

	}

	public void printStackTrace()
	{
		Throwable ex = getInnermostException();
		
		if(ex == this)
		{
			super.printStackTrace();
		}
		else
		{
			ex.printStackTrace();
		}
	}
	
	public void printStackTrace(PrintStream s)
	{
		Throwable ex = getInnermostException();
		
		if(ex == this)
		{
			super.printStackTrace(s);
		}
		else
		{
			ex.printStackTrace(s);
		}
	}
	
	public void printStackTrace(PrintWriter s)
	{
		Throwable ex = getInnermostException();
		
		if(ex == this)
		{
			super.printStackTrace(s);
		}
		else
		{
			ex.printStackTrace(s);
		}
	}


	private BaseExceptionData _exceptionData;
}
