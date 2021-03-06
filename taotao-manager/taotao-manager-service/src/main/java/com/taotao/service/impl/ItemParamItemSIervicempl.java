package com.taotao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.service.ItemParamItemService;

@Service
public class ItemParamItemSIervicempl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public String getItemParamByItemId(long itemId) {
		//根据商品ID查询商品规格参数
		TbItemParamItemExample itemExample = new TbItemParamItemExample();
		Criteria criteria = itemExample.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(itemExample);
		if (list == null && list.size()==0) {
			return "";
		}
		TbItemParamItem item = list.get(0);
		String paramData = item.getParamData();
		//生成html
		//吧规格参数json数据转换成java对象
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table border=\"1\" cellpadding=\"0\" cellspacing=\"1\" class=\"Ptable\" width=\"100%\">\n" );
		sb.append("    <tbody>\n" );
		for (Map m1 : jsonList) {
			sb.append("        <tr>\n" );
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n" );
			sb.append("        </tr>\n" );
			List<Map> list2 = (List<Map>) m1.get("params");
			for (Map m2 : list2) {
				sb.append("        <tr>\n" );
				sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n" );
				sb.append("            <td>"+m2.get("v")+"</td>\n" );
				sb.append("        </tr>\n" );
			}
		}
		sb.append("    </tbody>\n" );
		sb.append("</table>");
		return sb.toString();
	}

}
