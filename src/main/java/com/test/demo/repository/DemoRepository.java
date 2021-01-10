package com.test.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.test.demo.entity.Demo;

@Repository
public class DemoRepository {
	public List<Demo> demo = new ArrayList<Demo>();

	public List<Demo> getAll() {
		return demo;
	}

	public String add(Demo demo2) {
		demo.add(demo2);
		return "success add";
	}

	public String edit(Demo demo2) {
		demo.stream().filter(d -> d.getId() == demo2.getId()).forEach(d -> {
			d.setAddress(demo2.getAddress());
			d.setName(demo2.getName());
		});
		return "success update";
	}

	public String delete(int id) {
		demo.remove(id-1);
		return "Deleted";				
	}
}
