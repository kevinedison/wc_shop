package com.lichking.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lichking.itf.service.ICommodityTypeService;
import com.lichking.pojo.CommodityTypePOJO;
import com.lichking.pojo.ResultPOJO;

@Controller
@RequestMapping("/back/data")
public class BMDataContronller {
	
	@Resource
	private ICommodityTypeService commodityTypeService;
	
	private Logger log = Logger.getLogger(BMDataContronller.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getAllTypes")
	public @ResponseBody ResultPOJO<List> getAllCommodityTypes(HttpServletResponse res) throws Exception{
		log.info("请求json数据：/back/data/getAllTypes");
		
		ResultPOJO result = getTypes();
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/addTypes")
	public @ResponseBody ResultPOJO<List> addCommodityTypes(HttpServletResponse res,@RequestBody CommodityTypePOJO type){
		log.info("添加商品类型：/back/data/addTypes");
		if(canAdd(type)){
			this.commodityTypeService.insertComType(type);
			ResultPOJO result = getTypes();
			result.setMsg("新增商品成功!");
			log.info("新增商品成功!");
			return result;
		}
		ResultPOJO result = new ResultPOJO<>();
		result.setMsg("该商品类型已存在！新增失败！");
		result.setResult(false);
		log.info("该商品类型已存在！新增失败！");
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResultPOJO<List> getTypes(){
		List<CommodityTypePOJO> c_list = this.commodityTypeService.getAllTypes();
		ResultPOJO result = new ResultPOJO<>();
		result.setMsg("");
		result.setResult(true);
		result.setT(c_list);
		return result;
	}

	public boolean canAdd(CommodityTypePOJO type){
		ResultPOJO result = getTypes();
		List<CommodityTypePOJO> list = (List<CommodityTypePOJO>) result.getT();
		for(CommodityTypePOJO t : list){
			if(t.getType().equals(type.getType()));
				return false;
		}
		return true;
	}
	
}
