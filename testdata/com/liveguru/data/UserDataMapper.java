package com.liveguru.data;

import java.io.File;
import java.util.Collection;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstant;

public class UserDataMapper {

	public static UserDataMapper getUserData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			List<UserDataMapper> listData = mapper.readValue(new File(GlobalConstant.PROJECT_PATH + "/resources/UserData.json"), 
//					new TypeReference<List<UserDataMapper>>(){});
//			for (UserDataMapper userData : listData) {
//				return userData;
//			}
			return mapper.readValue(new File(GlobalConstant.PROJECT_PATH + "/resources/UserData.json"), UserDataMapper.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@DataProvider(name = "getData")
    public Object[] getData() {
        return readUserData().toArray();
    }
	
	public List<UserDataMapper> readUserData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String filePath = new File(GlobalConstant.PROJECT_PATH + "/resources/UserData.json").getAbsolutePath();
            JavaType typeOfT = mapper.getTypeFactory().constructCollectionType(Collection.class, UserDataMapper.class);
            return mapper.readValue(new File(filePath), typeOfT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	

	@JsonProperty("first_name")
	private String first_name;

	@JsonProperty("last_name")
	private String last_name;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
