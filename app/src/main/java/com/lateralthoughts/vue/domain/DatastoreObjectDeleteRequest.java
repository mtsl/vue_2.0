package com.lateralthoughts.vue.domain;


import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class DatastoreObjectDeleteRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Getter @Setter private ArrayList<Long> userIds;

}