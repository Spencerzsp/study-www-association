package com.shiant.study.core.system.service;

import java.util.Map;

import com.shiant.common.exception.ServiceException;
import com.shiant.rmi.user.vo.UserRmiVo;
import com.shiant.study.core.system.vo.CarouselVo;

public interface CarouselService {

	/**
	 * <p> @方法描述：    根据编号获取轮播			</p>
	 * <p> @创建人：        AkatsukiSimo Xie		</p>
	 * <p> @创建时间：    2020年6月1日 下午5:13:25	</p>
	 * <p> @修改人：        AkatsukiSimo Xie		</p>
	 * <p> @修改时间：    2020年6月1日 下午5:13:25	</p>
	 * @param id	编号
	 * @param user	用户信息
	 * @return
	 * @throws ServiceException
	 */
	CarouselVo getBean(Long id,UserRmiVo user) throws ServiceException;
	
	/**
	 * <p> @方法描述：    获取轮播				</p>
	 * <p> @创建人：        AkatsukiSimo Xie		</p>
	 * <p> @创建时间：    2020年6月1日 下午5:13:25	</p>
	 * <p> @修改人：        AkatsukiSimo Xie		</p>
	 * <p> @修改时间：    2020年6月1日 下午5:13:25	</p>
	 * @param from 			当前页
	 * @param size			每页总页数
	 * @param condition		其他条件
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	Map<String,Object> getBeans(int from,int size,String condition,UserRmiVo user) throws ServiceException;
	
	/**
	 * <p> @方法描述：    删除轮播				</p>
	 * <p> @创建人：        AkatsukiSimo Xie		</p>
	 * <p> @创建时间：    2020年6月1日 下午5:13:25	</p>
	 * <p> @修改人：        AkatsukiSimo Xie		</p>
	 * <p> @修改时间：    2020年6月1日 下午5:13:25	</p>
	 * @param id	编号
	 * @param user  用户信息
	 * @throws ServiceException
	 */
	void deleteBean(Long id ,UserRmiVo user) throws ServiceException;
	
	/**
	 * <p> @方法描述：    添加轮播					</p>
	 * <p> @创建人：        AkatsukiSimo Xie		</p>
	 * <p> @创建时间：    2020年6月1日 下午5:13:25	</p>
	 * <p> @修改人：        AkatsukiSimo Xie		</p>
	 * <p> @修改时间：    2020年6月1日 下午5:13:25	</p>
	 * @param bean	轮播信息
	 * @param user  用户信息
	 * @throws ServiceException
	 */
	void addBean(CarouselVo bean,UserRmiVo user) throws ServiceException;
	
	/**
	 * <p> @方法描述： 更新轮播	                </p>
	 * <p> @创建人：        AkatsukiSimo Xie		</p>
	 * <p> @创建时间：    2020年6月1日 下午5:13:25	</p>
	 * <p> @修改人：        AkatsukiSimo Xie		</p>
	 * <p> @修改时间：    2020年6月1日 下午5:13:25	</p>
	 * @param bean	轮播信息
	 * @param user 	用户信息
	 * @throws ServiceException
	 */
	void updateBean(CarouselVo bean,UserRmiVo user) throws ServiceException;
}
