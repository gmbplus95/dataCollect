package com.ifisolution.restcontroller;

public class Payload {
		private Object data;
		private String status;
		private String message;
		private Boolean error;
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Boolean getError() {
			return error;
		}
		public void setError(Boolean error) {
			this.error = error;
		}
		public Payload() {
			super();
		}
		public Payload(Object data, String status, String message, Boolean error) {
			super();
			this.data = data;
			this.status = status;
			this.message = message;
			this.error = error;
		}
		
		
		
}
