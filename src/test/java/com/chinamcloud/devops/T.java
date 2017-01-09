package com.chinamcloud.devops;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

import com.chinamcloud.devops.utils.mapper.JsonMapper;
import com.chinamcloud.devops.web.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class T {

	public static JsonMapper binder = JsonMapper.nonEmptyMapper();

	public String Str = "{\"resp_info\": \"{\\\"return\\\": [{\\\"Ecs-L0dNQYd0\\\": \\\"root\\\",\\\"Ecs-BHeGsBo8\\\": \\\"sobeydemo\\\\administrator\\\"}]}\", \"taskCode\": \"WorkFlowTask-d4816104cd95416b\", \"resultMessage\": \"Message Received!\", \"taskStatus\": 200}";

	public String Str2 = "{\"return\": [{\"Ecs-L0dNQYd0\": \"root\",\"Ecs-BHeGsBo8\": \"sobeydemo\\administrator\"}]}";

	/**
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	@Test
	public void aa() throws JsonProcessingException, IOException {

		Result r = binder.fromJson(Str, Result.class);

//		System.out.println(Str);
//		System.out.println(r.getResp_info());
		// System.out.println(r.getResultMessage());
		// System.out.println(r.getTaskCode());
		// System.out.println(r.getTaskStatus());

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(Str);
//		System.out.println(node.get("resp_info"));

		Iterator<Entry<String, JsonNode>> jsonNodes = node.fields();

		while (jsonNodes.hasNext()) {
			Entry<String, JsonNode> node2 = jsonNodes.next();
			System.out.println(node2.getKey());
			System.out.println(node2.getValue().toString());
		}

	}
}
