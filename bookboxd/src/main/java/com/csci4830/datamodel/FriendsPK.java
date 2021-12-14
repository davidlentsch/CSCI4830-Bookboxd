package com.csci4830.datamodel;

import java.io.Serializable;

public class FriendsPK implements Serializable {
	protected Integer user_id_1;
	protected Integer user_id_2;
	
	public FriendsPK() {}

	public FriendsPK(Integer user_id_1, Integer user_id_2) {
		super();
		this.user_id_1 = user_id_1;
		this.user_id_2 = user_id_2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user_id_1 == null) ? 0 : user_id_1.hashCode());
		result = prime * result + ((user_id_2 == null) ? 0 : user_id_2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FriendsPK other = (FriendsPK) obj;
		if (user_id_1 == null) {
			if (other.user_id_1 != null)
				return false;
		} else if (!user_id_1.equals(other.user_id_1))
			return false;
		if (user_id_2 == null) {
			if (other.user_id_2 != null)
				return false;
		} else if (!user_id_2.equals(other.user_id_2))
			return false;
		return true;
	}
	
	
}