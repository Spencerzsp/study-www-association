package com.shiant.study.core.system.service.impl;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shiant.common.exception.DaoException;
import com.shiant.common.exception.ServiceException;
import com.shiant.common.util.StringUtil;
import com.shiant.rmi.user.vo.UserRmiVo;
import com.shiant.study.core.system.dao.CarouselDAO;
import com.shiant.study.core.system.dao.CarouselRepository;
import com.shiant.study.core.system.model.Carousel;
import com.shiant.study.core.system.service.CarouselService;
import com.shiant.study.core.system.transfer.CarouselTransfer;
import com.shiant.study.core.system.vo.CarouselVo;


@Service(value = "/carouselService")
public class CarouselServiceImpl implements CarouselService {

	@Autowired
	private CarouselDAO carouselDAO;

	@Qualifier("carouselRepository")
	@Autowired
	private CarouselRepository carouselRepository;

	private static CarouselTransfer transfer = new CarouselTransfer();
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addBean(CarouselVo bean, UserRmiVo user) throws ServiceException {
		try {
			Carousel entity = transfer.transferVoToEntity(bean);
			entity.setId(null);
			entity.setTitle(bean.getTitle());
			entity.setCreateDate(new Date());
			entity.setCreater(user.getUserRealName());
			entity.setUpdateDate(new Date());
			entity.setUpdater(user.getUserRealName());
			entity.setDelete(false);
			
			carouselDAO.persistent(entity);
		} catch (DaoException e) {
			if (e.getErrorMsg() == null) {
				e.setErrorMsg("添加轮播");
			}
			throw new ServiceException (e.getErrorMsg(), e);
		} catch (ServiceException e) {
			if (e.getErrorMsg() ==  null) {
				e.setErrorMsg("添加轮播失败");
			}
			throw e;
		} catch (Exception e) {
			throw new ServiceException("添加轮播失败!", e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, Object> getBeans(int from,int size,String condition,UserRmiVo user) throws ServiceException {
		try {
			Map<String,Object> maps = new HashMap<>();
			if(StringUtil.isNotBlank(condition)) {
				condition = URLDecoder.decode(URLDecoder.decode(condition, "utf8"), "utf8");
			}
			Sort sort = Sort.by(Direction.ASC, "order");
			Pageable pageable = PageRequest.of(from-1, size, sort);
			Page<Carousel> beans = carouselRepository.findByOrgNameStartingWith(user.getOrgName(), pageable);
			List<CarouselVo> vos = (List<CarouselVo>) 
					transfer.transferCollectionEntityToBaseVos(beans.getContent());
			maps.put("beans", vos);
			return maps;
		}catch(Exception e){
			throw new ServiceException("根据条件分页获取轮播集合失败!",e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = Exception.class)
	public CarouselVo getBean(Long id, UserRmiVo user) throws ServiceException {
		try {
			CarouselVo Vo = transfer.transferEntityToBaseVo(carouselDAO.findById(id));
			return Vo;
		}catch(DaoException e){
			if(e.getErrorMsg() == null){
				e.setErrorMsg("根据轮播编号获取实体数据操作失败!");
			}
			throw new ServiceException(e.getErrorMsg(),e);
		}catch(Exception e){
			throw new ServiceException("根据轮播编号获取实体失败!",e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteBean(Long id,UserRmiVo user) throws ServiceException {
		try {
			Carousel bean = carouselDAO.findById(id);
			bean.setDelete(true);
			bean.setUpdateDate(new Date());
			bean.setUpdater(user.getUserRealName());
//			carouselDAO.remove(carouselDAO.findById(id));
		} catch (DaoException e) {
			if (e.getErrorMsg() == null) {
				e.setErrorMsg("删除轮播数据操作失败!");
			}
			throw new ServiceException(e.getErrorMsg(),e);
		} catch (Exception e) {
			throw new ServiceException("删除轮播失败!",e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void updateBean(CarouselVo vo, UserRmiVo user) throws ServiceException {
		try {
			Carousel bean = carouselDAO.findById(vo.getId());
			if(bean != null) {
				bean.setUpdater(user.getUserRealName());
				bean.setUpdateDate(new Date());
				bean.setOrgName(user.getOrgName());
				bean.setTitle(vo.getTitle());
				bean.setUrl(vo.getUrl());
				bean.setFileUrl(vo.getFileUrl());
				bean.setOrder(vo.getOrder());
			}
		}catch(DaoException e){
			if(e.getErrorMsg() == null){
				e.setErrorMsg("更新轮播数据操作失败!");
			}
			throw new ServiceException(e.getErrorMsg(),e);
		}catch(Exception e){
			throw new ServiceException("更新轮播失败!",e);
		}
		
	}

}
