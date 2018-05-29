package com.kyle.water.dictionary.controller;

import com.kyle.framework.controllor.IBaseControllor;
import com.kyle.water.dictionary.entity.DictionaryEntity;
import com.kyle.water.dictionary.dao.IDictionaryDao;
import com.kyle.water.dictionary.service.IDictionaryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *  Created by kyle.
 *
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController extends IBaseControllor<DictionaryEntity, IDictionaryDao, IDictionaryService> {


}
