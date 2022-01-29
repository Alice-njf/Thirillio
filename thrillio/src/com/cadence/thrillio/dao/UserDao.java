package com.cadence.thrillio.dao;

import com.cadence.thrillio.DataStore;
import com.cadence.thrillio.entities.User;

public class UserDao {

		public User[] getUsers() {
			return DataStore.getUsers();
		}
}
