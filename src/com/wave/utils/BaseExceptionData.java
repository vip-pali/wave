package com.wave.utils;

import java.lang.reflect.UndeclaredThrowableException;
import java.rmi.RemoteException;

/**
 * This class represents the data parts of the Base exceptions to be thrown.
 * This class is necessary as Base Exceptions need to be sometimes Runtime
 * Exceptions and sometimes just Exceptions. This class exists to prevent
 * copy-pasted code.
 */
public class BaseExceptionData implements java.io.Serializable {

	public BaseExceptionData(Throwable toWrap) {
		this(toWrap, null);
	}

	public BaseExceptionData(String message) {
		this(null, message);
	}

	/**
	 * Creates a BaseExceptionData object wrapping the specified exception to be
	 * wrapped. Nothing is wrapped if <em>toWrap</em> is null. See parameter
	 * documentation for various possibilities.
	 * 
	 * @param toWrap
	 *            the exception to be wrapped. Nothing is wrapped if null is
	 *            passed.
	 * @param message
	 *            the message.
	 */
	public BaseExceptionData(Throwable toWrap, String message) {
		this(toWrap, null, message);
	}

	public BaseExceptionData(Throwable toWrap, String code, String message) {
		_wrappedException = toWrap;
		_code = code;
		_message = message;
	}

	/**
	 * Does this exception wrap another exception?
	 */
	public boolean isAWrapper() {
		return (_wrappedException != null);
	}

	public boolean isABaseThrowableWrapper() {
		return (isAWrapper() && (getWrappedException() instanceof BaseThrowable));
	}

	/**
	 * Get the wrapped exception if any, else get null.
	 */
	public Throwable getWrappedException() {
		return _wrappedException;
	}

	/**
	 * Get the message code. Delegates the call to the underlying exception if
	 * the <em>message</em> of <em>this</em> exception is <em>null</em> and the
	 * underlying exception is a <em>BaseExceptionData</em>.
	 */
	public String getMessage() {
		return ((_message != null) ? _message
				: (((_wrappedException != null) && (_wrappedException instanceof BaseThrowable)) ? ((BaseThrowable) _wrappedException)
						.getMessage()
						: (((_wrappedException != null) ? (_wrappedException
								.getMessage()) : ("NO MESSAGE")))));
	}

	/**
	 * Gets _code
	 */
	public String getCode() {
		if (_code == null)
			return "";
		else
			return _code;
	}

	/**
	 * Sets _code
	 */
	public void setCode(String code) {
		this._code = code;
	}

	/*
	 * private static String[] makeArray(Throwable e) { if(e==null) return new
	 * String[0]; else { String retVal[] = {e.getMessage()}; return retVal; } }
	 */
	public Throwable getInnermostException() {
		return getInnermostThrowable(_wrappedException);
	}

	public static Throwable getInnermostThrowable(Throwable t) {
		if (t == null) {
			return null;
		}

		if (t instanceof BaseException) {
			BaseException d = (BaseException) t;

			if (d.getWrappedException() instanceof StackOverflowError) {
				return d.getWrappedException();
			}

			if (d.getInnermostException() != null
					&& d != d.getInnermostException()) {
				t = d.getInnermostException();
				return getInnermostThrowable(t);
			}
		} else if (t instanceof BaseRuntimeException) {
			BaseRuntimeException d = (BaseRuntimeException) t;

			if (d.getWrappedException() instanceof StackOverflowError) {
				return d.getWrappedException();
			}

			if (d.getInnermostException() != null
					&& d != d.getInnermostException()) {
				t = d.getInnermostException();
				return getInnermostThrowable(t);
			}
		} else if (t instanceof RemoteException) {
			RemoteException r = (RemoteException) t;
			if (r.detail != null && r.detail != r) {
				t = r.detail;
				return getInnermostThrowable(t);
			}

		} else if (t instanceof UndeclaredThrowableException) {
			UndeclaredThrowableException u = (UndeclaredThrowableException) t;
			if (u.getUndeclaredThrowable() != null
					&& u.getUndeclaredThrowable() != u) {
				t = u.getUndeclaredThrowable();
				return getInnermostThrowable(t);
			}
		}

		else if (t instanceof javax.management.RuntimeErrorException) {
			javax.management.RuntimeErrorException e = (javax.management.RuntimeErrorException) t;
			if (e.getCause() != null && e.getCause() != e) {
				t = e.getCause();
				return getInnermostThrowable(t);
			} else if (e.getTargetError() != null
					&& e.getTargetError() != (Throwable) e) {
				t = e.getTargetError();
				return getInnermostThrowable(t);
			}
		} else if (t instanceof java.lang.StackOverflowError) {
			return t;
		} else {
			if (t.getCause() != null && t.getCause() != t) {
				return getInnermostThrowable(t.getCause());
			}
		}

		return t;
	}

	private Throwable _wrappedException;
	private String _message;
	private String _code;

}
