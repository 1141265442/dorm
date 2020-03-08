package com.zxhan.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zxhan
 * @since 2020-02-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Repair extends Model<Repair> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 寝室id
     */
    private Integer roomId;

    /**
     * 报修设备
     */
    private String repairDevice;

    /**
     * 报修说明
     */
    private String des;

    /**
     * 报修状态
     */
    private String status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
