package com.kyle.water.activity.entity;

import com.kyle.water.activityEnrollField.entity.ActivityEnrollFieldEntity;
import lombok.Data;

import java.util.List;

/**
 * @author -- kyle
 *         5/30/18 06:34
 */
@Data
public class ActivityDetail extends ActivityEntity{
    private List<ActivityEnrollFieldEntity> fields;
}
