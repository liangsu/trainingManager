package edu.lsnu.service;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.FreeTrainingBase;

public interface FreeTrainingBaseService extends DaoSupport<FreeTrainingBase>{

	int AddandGet(FreeTrainingBase freeTrainingBase);
	
	FreeTrainingBase getByNamdAndAddress(FreeTrainingBase freeTrainingBase);
}
