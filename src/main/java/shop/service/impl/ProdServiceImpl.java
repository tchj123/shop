package shop.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.bean.cart.CartItemDO;
import shop.bean.order.OrderItemInfo;
import shop.bean.product.ProductVO;
import shop.bean.product.Sku;
import shop.bean.product.SkuProperty;
import shop.mapper.ProdMapper;
import shop.mapper.SkuMapper;
import shop.service.ProdService;

@Service
public class ProdServiceImpl implements ProdService {
	@Autowired
	ProdMapper prodMapper;
	@Autowired
	SkuMapper skuMapper;
	@Override
	public String getProdName(int prodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProdState(int prodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Sku> getSkuMap(int prodId) {

		//获取skuList
		List<Sku> skuList=skuMapper.getSkuList(prodId);
		if(skuList.isEmpty())
			throw new RuntimeException("sku不存在");

		//获取skuId到sku的map
		Map<Integer, Sku> skuIdToSkuMap=new HashMap<>();
		for (Sku sku : skuList) {
			skuIdToSkuMap.put(sku.getSkuId(), sku);
		}

		//获取skuIdToSkprIdMap
		List<Integer> skuIdList=new LinkedList<>(skuIdToSkuMap.keySet());
		Map<Integer,String> skuIdToSkprIdMap=new HashMap<>();
		for(int skuId:skuIdList) {
			List<Integer> skprIdList = skuMapper.getSkprIdList(skuId);
			if(skprIdList.isEmpty())
				throw new RuntimeException("skprId不存在");
			//将skprId拼接成String串
			String skprId=skprIdList.stream().map(String::valueOf).reduce((s1,s2)->{return s1+s2;}).orElseThrow(RuntimeException::new);
			skuIdToSkprIdMap.put(skuId,skprId);
		}

		//获取skprId串到sku的map
		Map<String,Sku> skuMap=new HashMap<>();
		for(Map.Entry<Integer,Sku> entry:skuIdToSkuMap.entrySet()){
			skuMap.put(skuIdToSkprIdMap.get(entry.getKey()),entry.getValue());
		}
		return skuMap;
	}


	@Override
	public List<String> getProdState(List<Integer> prodIdList) {
		return prodMapper.getProdStateList(prodIdList);
	}

	@Override
	public int getProdId(int skuId) {
		return skuMapper.getProdId(skuId).orElse(-1);
	}

	@Override
	public boolean hasProdId(int prodId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasProdId(List<Integer> prodIdList) {
		int num=prodMapper.getCountOfProdId(prodIdList);
		return num==prodIdList.size();
	}



	@Override
	public List<Integer> getPrice(List<Integer> skuIdList) {
		return skuMapper.getPriceList(skuIdList);
	}

	@Override
	@Transactional(rollbackFor=Exception.class) 
	public boolean reduceStock(List<Integer> skuIdList,List<Integer> numList) {
		for(int i=0;i<skuIdList.size();i++) {
			//用for update语句锁定库存
			int stock= skuMapper.selectStockForUpdate(skuIdList.get(i)).orElseThrow(RuntimeException::new);
			if(stock<numList.get(i) 
					|| !(skuMapper.reduceStock(skuIdList.get(i), numList.get(i))==1))
				throw new RuntimeException("库存不足");
		}

		return true;
	}

	@Override
	public ProductVO getProduct(int prodId) {
		//获取商品
		ProductVO product=prodMapper.getProduct(prodId).orElse(null);
		//如果商品不存在，抛出异常
		if(product==null)
			throw new RuntimeException("没有该商品");

		//获取sku属性集
		List<SkuProperty> skuPropertyList= skuMapper.getSkuPropertyList(prodId);
		Map<String,List<SkuProperty>> skuPropertyMap=new HashMap<>();
		for(SkuProperty prop:skuPropertyList){
			String name=prop.getName();
			if(skuPropertyMap.containsKey(name))
				skuPropertyMap.get(name).add(prop);
			else
				skuPropertyMap.put(name,Stream.of(prop).collect(Collectors.toList()));
		}

		product.setSkuPropertyMap(skuPropertyMap);

		return product;
	}


	@Override
	public Sku getSku(int prodId,List<Integer> skprIdList) {
		if(skprIdList==null || skprIdList.isEmpty())
			throw new RuntimeException("skprIdList为空");

		Map<String,Sku> skuMap=getSkuMap(prodId);
		Collections.sort(skprIdList);
		String skprId=skprIdList.stream().map(String::valueOf).reduce((s1,s2)->{return s1+s2;}).orElseThrow(RuntimeException::new);
		if(!skuMap.containsKey(skprId))
			throw new RuntimeException("商品不存在");
		return skuMap.get(skprId);
	}

	@Override
	public List<OrderItemInfo> getOrderItemInfo(List<Integer> prodIdList) {
		return prodMapper.getOrderItemInfo(prodIdList);
	}


}
