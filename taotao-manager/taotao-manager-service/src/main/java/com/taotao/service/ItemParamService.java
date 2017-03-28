package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
	
	TaotaoResult getItemParamCid(long cid);
	
	EUDataGridResult getItemParamList(int page,int rows);
	
	TaotaoResult insertItemParam(TbItemParam itemParam);
}
