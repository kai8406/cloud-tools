package com.chinamcloud.devops;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.chinamcloud.devops.utils.mapper.JsonMapper;
import com.chinamcloud.devops.web.Result;
import com.fasterxml.jackson.core.JsonProcessingException;

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

		System.out.println(r.getResp_info());

		String json = StringUtils.substring(r.getResp_info(), 12, r.getResp_info().length() - 2);
		
		System.out.println(json);

		ResMap map = binder.fromJson(json, ResMap.class);

		System.out.println(map);

	}
}
