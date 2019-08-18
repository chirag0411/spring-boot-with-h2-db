package com.dataone.Tesk1JsonTransformer.controller;

import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("restriction")
@RestController
@RequestMapping("/task1")
public class JsonTransformerController {

	private final String arrayClassString = "class java.util.ArrayList";

	@PutMapping("/alpha")
	public ResponseEntity<?> alphabetizeTheKey(@RequestBody TreeMap<?, ?> requestJsonObj) {
		return ResponseEntity.ok(requestJsonObj);
	}

	@PostMapping("/flatten")
	public ResponseEntity<?> flattenArray(@RequestBody Map<Object, Object> requestJsonObj) {
		for (Map.Entry<Object, Object> map : requestJsonObj.entrySet()) {
			if (map.getValue().getClass().toString().equals(arrayClassString)) {

				String tempString = map.getValue().toString();

				if (tempString != null && tempString.length() > 0
						&& tempString.charAt(tempString.length() - 1) == ']') {
					tempString = tempString.substring(1, tempString.length() - 1);
				}
				requestJsonObj.put(map.getKey(), tempString);
			}
		}
		return ResponseEntity.ok(requestJsonObj);
	}

	@GetMapping("/status")
	public ResponseEntity<?> getHealthStatus() throws Exception {

		long total = 0, used = 0;
		File[] roots = File.listRoots();

		List<Map<String, String>> discSpaceAvail = new ArrayList<>();

		for (File root : roots) {
			Map<String, String> discSpaceAvailProperties = new HashMap<String, String>();

			discSpaceAvailProperties.put("discname", root.getAbsolutePath());
			discSpaceAvailProperties.put("availbytes", Long.toString(root.getFreeSpace()));

			total += root.getTotalSpace();
			used += (root.getTotalSpace() - root.getFreeSpace());

			discSpaceAvail.add(discSpaceAvailProperties);
		}

		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();

		Map<String, Object> resObj = new HashMap<String, Object>();

		resObj.put("mem-used-pct", Math.round((((double) used / (double) total) * 100) * 100.0) / 100.0);
		resObj.put("disc-space-avail", discSpaceAvail);
		resObj.put("cpu-used-pct", operatingSystemMXBean.getSystemCpuLoad());
		return ResponseEntity.ok(resObj);
	}
}