package edu.lsnu.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.FreeTrainingBase;
import edu.lsnu.service.FreeTrainingBaseService;
import edu.lsnu.utils.StringUtil;

@Service
@Transactional
public class FreeTrainingBaseServiceImpl extends DaoSupportImpl<FreeTrainingBase> implements FreeTrainingBaseService{

	@Override
	public int AddandGet(FreeTrainingBase freeTrainingBase) {
		int id = 0;
		FreeTrainingBase oldBean = getByNamdAndAddress(freeTrainingBase);
		if(oldBean == null){
			freeTrainingBase.setAddTime(new Date());
			id = super.add(freeTrainingBase);
		}else{
			id = oldBean.getId();
		}
		return id;
	}

	@Override
	public FreeTrainingBase getByNamdAndAddress(FreeTrainingBase freeTrainingBase) {
		if(freeTrainingBase != null && StringUtil.isNotBank(freeTrainingBase.getName())
				&& StringUtil.isNotBank(freeTrainingBase.getAddress())){
			String hql = "from FreeTrainingBase where name = ? and address = ?";
			List<FreeTrainingBase> list = super.getList(hql, freeTrainingBase.getName(), freeTrainingBase.getAddress());
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}
		return null;
	}
	
	

}
