package dto;

public class userTokenDTO {
	

		private Integer id;
		
		private String email;
		
		private String token;

		
		public userTokenDTO () {
		
		}

		public userTokenDTO(Integer id, String email, String token) {
			super();
			this.id = id;
			this.email = email;
			this.token = token;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
		

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}


