package edu.lsnu.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.TrainingItem;
import edu.lsnu.service.TrainingItemService;

@Service
@Transactional
public class TrainingItemServiceImpl extends DaoSupportImpl<TrainingItem> implements TrainingItemService {

}
