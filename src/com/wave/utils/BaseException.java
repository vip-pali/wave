
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
* <dl>
* 	<dt><em>isAWrapper</em></dt>
* 	<dd> to check weather the instance wraps an uderlying
* 	exception.</dd>
* 	<dt><em>getWrappedException</em></dt>
* 	<dd>to obtain the underlying
* 	exception.</dd>
* </dl>
*/

public class BaseException
	extends Exception
	implements BaseThrowable,  java.io.Serializable

{
	/**
	* @see #BaseException(Exception,String,String[]).
	*/
	public BaseException(Throwable toWrap)
	{
		this(toWrap,null);
	}

	/**
	* Creates a BaseException object wrapping the specified exception to be
	* wrapped. Nothing is wrapped if <em>toWrap</em> is null. See parameter
	* documentation for various possibilities.
	* @param toWrap the exception to be wrapped. Nothing is wrapped if null is
	* passed.
	* @param message the  main message. 
	*/
	public BaseException(Throwable toWrap,String message)
	{
		this(toWrap,null,message);
	}

	public BaseException(Throwable toWrap,String code,String message)
	{
		if(toWrap instanceof StackOverflowError)
		{
			toWrap.printStackTrace();
			throw new BaseRuntimeException("StackOverflowError");
		}

		_exceptionData = new BaseExceptionData(toWrap,code,message);
	}


	/**
	* @see #BaseException(Throwable,String,String[]).
	*/
	public BaseException(String message)
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
	* Returns the message of this exception. 
	*/
	public String getMessage()
	{
		String retVal = _exceptionData.getMessage();
		if ((retVal != null) && (retVal.length() > 500))
		{
			retVal = retVal.substring(0, 300);
		}
		
		return retVal;
	}


	public String getCode()
	{
		return _exceptionData.getCode();
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
