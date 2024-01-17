package frame.sys.dash.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import frame.sys.dash.service.SysDashService;
import frame.fcom.service.impl.ComDAO;
import frame.futil.ValidateUtil;

@Service("sysDashService")
public class SysDashServiceImpl implements SysDashService{

	@Resource(name="comDAO")
	private ComDAO comDAO;
	
	@Resource(name = "validateUtil")
	private ValidateUtil validateUtil;
	
	/**
	 * <pre>
	 * 1. 메소드명 : regSysDashUDtl
	 * 2. 작성일 : 2021. 5. 21. 오전 11:53:18
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 대시보드관리 저장
	 * </pre>
	 * @param paramData
	 */
	@Override
	public void regSysDashUDtl(String paramData) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Map<String, Object>> jsonObject = mapper.readValue(paramData, Map.class);
		
		List<HashMap<String, Object>> rowDatas = (List) jsonObject.get("rowDatas");
		
		for (int i = 0; i < rowDatas.size(); i++) {
			HashMap<String, Object> map = new HashMap<>();
			map.putAll(rowDatas.get(i));
			map.put("sqlQueryId", "sysDashDAO.regSysDashUDtl");
			comDAO.updateCommonQuery(map);
		}
		
	}

}