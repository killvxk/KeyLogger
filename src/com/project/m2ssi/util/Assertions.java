package com.project.m2ssi.util;

import java.util.ArrayList;
import java.util.List;
public final class Assertions {
    
	public static boolean require(boolean cond, String desc, Object... args) {
		
		assert requireNotNull(desc);
		
		if (assertionsEnabled() && !cond) {
			
			throw fromCaller(new PreconditionException(
				String.format(desc, args)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean require(boolean cond) {
		return require(cond, "");
	}

	public static boolean requireNotNull(Object object, String name) {
		
		if (assertionsEnabled() && object == null) {
			
			throw fromCaller(new PreconditionException(
				nullExceptionMessage(name)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean requireNotNull(Object object) {
		return requireNotNull(object, "");
	}
	
	public static boolean requireEquals(
			Object object, 
			Object expected, 
			String desc,
			Object... args) {
		
		assert requireNotNull(object);
		assert requireNotNull(expected);
		assert requireNotNull(desc);
		
		return require(object.equals(expected), desc, args);
		
	}
	
	public static boolean requireEquals(Object object, Object expected) {
		
		assert requireNotNull(object);
		assert requireNotNull(expected);
		
		if (assertionsEnabled() && !object.equals(expected)) {
			
			throw fromCaller(new PreconditionException(
				equalityExceptionMessage(object, expected)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean requireNotEquals(
			Object object, 
			Object value, 
			String desc,
			Object... args) {
		
		assert requireNotNull(object);
		assert requireNotNull(value);
		assert requireNotNull(desc);
		
		return require(!object.equals(value), desc, args);
		
	}
	
	public static boolean requireNotEquals(Object object, Object value) {
		
		assert requireNotNull(object);
		assert requireNotNull(value);
		
		if (assertionsEnabled() && object.equals(value)) {
			
			throw fromCaller(new PreconditionException(
				equalityExceptionMessage(object, value)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean check(boolean cond, String desc, Object... args) {
		
		assert requireNotNull(desc);
		
		if (assertionsEnabled() && !cond) {
			
			throw fromCaller(new InvariantException(
				String.format(desc, args)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean check(boolean cond) {
		return check(cond, "");
	}
	
	public static boolean checkNotNull(Object object, String name) {
		
		if (assertionsEnabled() && object == null) {
			
			throw fromCaller(new InvariantException(
				nullExceptionMessage(name)
			));
			
		}
		
		return true;
		
	}
	public static boolean checkNotNull(Object object) {
		return checkNotNull(object, "");
	}
	
	public static boolean checkEquals(
			Object object, 
			Object expected, 
			String desc,
			Object... args) {
		
		assert requireNotNull(object);
		assert requireNotNull(expected);
		assert requireNotNull(desc);
		
		return check(object.equals(expected), desc, args);
		
	}
	
	public static boolean checkEquals(Object object, Object expected) {
		
		assert requireNotNull(object);
		assert requireNotNull(expected);
		
		if (assertionsEnabled() && !object.equals(expected)) {
			
			throw fromCaller(new InvariantException(
				equalityExceptionMessage(object, expected)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean checkNotEquals(
			Object object, 
			Object value, 
			String desc,
			Object... args) {
		
		assert requireNotNull(object);
		assert requireNotNull(value);
		assert requireNotNull(desc);
		
		return check(!object.equals(value), desc, args);
		
	}
	
	public static boolean checkNotEquals(Object object, Object value) {
		
		assert requireNotNull(object);
		assert requireNotNull(value);
		
		if (assertionsEnabled() && object.equals(value)) {
			
			throw fromCaller(new InvariantException(
				equalityExceptionMessage(object, value)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean ensure(boolean cond, String desc, Object... args) {
		
		assert requireNotNull(desc);
		
		if (assertionsEnabled() && !cond) {
			
			throw fromCaller(new PostconditionException(
				String.format(desc, args)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean ensure(boolean cond) {
		return ensure(cond, "");
	}
	
	public static boolean ensureNotNull(Object object, String name) {
		
		if (assertionsEnabled() && object == null) {
			
			throw fromCaller(new PostconditionException(
				nullExceptionMessage(name)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean ensureNotNull(Object object) {
		return ensureNotNull(object, "");
	}
	
	public static boolean ensureEquals(
			Object object, 
			Object expected, 
			String desc,
			Object... args) {
		
		assert requireNotNull(object);
		assert requireNotNull(expected);
		assert requireNotNull(desc);
		
		return ensure(object.equals(expected), desc, args);
		
	}
	
	public static boolean ensureEquals(Object object, Object expected) {
		
		assert requireNotNull(object);
		assert requireNotNull(expected);
		
		if (assertionsEnabled() && !object.equals(expected)) {
			
			throw fromCaller(new PostconditionException(
				equalityExceptionMessage(object, expected)
			));
			
		}
		
		return true;
		
	}
	
	public static boolean ensureNotEquals(
			Object object, 
			Object value, 
			String desc,
			Object... args) {
		
		assert requireNotNull(object);
		assert requireNotNull(value);
		assert requireNotNull(desc);
		
		return ensure(!object.equals(value), desc, args);
		
	}
	
	public static boolean ensureNotEquals(Object object, Object value) {
		
		assert requireNotNull(object);
		assert requireNotNull(value);
		
		if (assertionsEnabled() && object.equals(value)) {
			
			throw fromCaller(new PostconditionException(
				equalityExceptionMessage(object, value)
			));
			
		}
		
		return true;
		
	}
	
	private static String nullExceptionMessage(String name) {
		
		if (name == null) {
			return "Null reference";
		} else {
			return String.format(name);
		}
		
	}
	
	private static String equalityExceptionMessage(
			Object object, 
			Object expected) {
		
		StringBuilder msg = new StringBuilder();
		
		msg.append("Expected \"");
		msg.append(expected.toString());
		msg.append("\" but was \"");
		msg.append(object.toString());
		msg.append("\"");
		
		return msg.toString();
		
	}
	
	private static boolean assertionsEnabled() {
		
		boolean enabled = false;
		assert(enabled = true);
		
		return enabled;
		
	}
	
	private static <E extends Throwable> E fromCaller(E ex) {
		
		List<StackTraceElement> stackTrace = new ArrayList<StackTraceElement>();
		
		for (StackTraceElement element : ex.getStackTrace()) {
			
			if (!element.getClassName().equals(Assertions.class.getName()))
				stackTrace.add(element);
			
		}
		
		ex.setStackTrace(
			stackTrace.toArray(
				new StackTraceElement[stackTrace.size()]
			)
		);
		
		return ex;
		
	}

	public static class PreconditionException extends AssertionError {
		public PreconditionException(String msg) {
			super(String.format(msg
			));
			
		}
		
	}
	public static class InvariantException extends AssertionError {
		public InvariantException(String msg) {
			super(msg);
		}
		
	}
	public static class PostconditionException extends AssertionError {
		public PostconditionException(String msg) {
			super(String.format(msg
			));
			
		}
		
	}
	
}
