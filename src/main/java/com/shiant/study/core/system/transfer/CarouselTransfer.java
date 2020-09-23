package com.shiant.study.core.system.transfer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.shiant.common.exception.ServiceException;
import com.shiant.common.transfer.BaseTransfer;
import com.shiant.study.core.system.model.Carousel;
import com.shiant.study.core.system.model.School;
import com.shiant.study.core.system.vo.CarouselVo;

public class CarouselTransfer implements BaseTransfer<Carousel,CarouselVo>{

	@Override
	public CarouselVo transferEntityToBaseVo(Carousel entity) throws ServiceException {
		try {
			if (entity == null) {
				return null;
			}
			CarouselVo vo = new CarouselVo();
			vo.setId(entity.getId());
			vo.setTitle(entity.getTitle());
			vo.setOrgName(entity.getOrgName());
			vo.setOrder(entity.getOrder());
			vo.setUrl(entity.getUrl());
			vo.setFileUrl(entity.getFileUrl());
			vo.setCreateDate(entity.getCreateDate());
			vo.setCreater(entity.getCreater());
			vo.setUpdateDate(entity.getUpdateDate());
			vo.setUpdater(entity.getUpdater());
			vo.setDelete(entity.isDelete());
			return vo;
		} catch (Exception e) {
			throw new ServiceException("网站名基本VO转换失败！", e);
		}
	}

	@Override
	public CarouselVo transferEntityToFullVo(Carousel entity) throws ServiceException {
		try {
			return transferEntityToBaseVo(entity);
		} catch (Exception e) {
			throw new ServiceException("网站名完整VO转换失败!", e);
		}
	}

	@Override
	public Carousel transferVoToEntity(CarouselVo vo) throws ServiceException {
		try {
			if (vo == null) {
				return null;
			}
			Carousel entity = new Carousel();
			entity.setId(vo.getId());
			entity.setTitle(vo.getTitle());
			entity.setOrgName(vo.getOrgName());
			entity.setOrder(vo.getOrder());
			entity.setUrl(vo.getUrl());
			entity.setFileUrl(vo.getFileUrl());
			entity.setCreateDate(vo.getCreateDate());
			entity.setCreater(vo.getCreater());
			entity.setUpdateDate(vo.getUpdateDate());
			entity.setUpdater(vo.getUpdater());
			entity.setDelete(vo.isDelete());
			return entity;
		} catch (Exception e) {
			throw new ServiceException("网站名转换失败",e);
		}
	}

	@Override
	public Collection<CarouselVo> transferCollectionEntityToBaseVos(Collection<Carousel> entitys) throws ServiceException {
		List<CarouselVo> vos = new ArrayList<CarouselVo>();
		if (entitys != null) {
			for (Carousel entity : entitys) {
				vos.add(transferEntityToBaseVo(entity));
			}
		}
		return vos;
	}

	@Override
	public Collection<CarouselVo> transferCollectionEntityToFullVos(Collection<Carousel> entitys) throws ServiceException {
		List<CarouselVo> vos = new ArrayList<CarouselVo>();
		if(entitys != null){
			for(Carousel entity : entitys){
				vos.add(transferEntityToFullVo(entity));
			}
		}
		return vos;
	}

	@Override
	public Collection<Carousel> transferCollectionVoToEntitys(Collection<CarouselVo> entitys) throws ServiceException {
		List<Carousel> vos = new ArrayList<>();
		if (entitys != null) {
			for (CarouselVo entity : entitys) {
				vos.add(transferVoToEntity(entity));
			}
		}
		return vos;
	}


}
