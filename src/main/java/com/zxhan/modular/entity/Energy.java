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
public class Energy extends Model<Energy> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 宿舍id
     */
    private Integer roomId;

    /**
     * 能源类型
     */
    private String energyType;

    /**
     * 用量
     */
    private String mount;

    /**
     * 缴费金额
     */
    private Float payMoney;

    /**
     * 是否缴费
     */
    private String pay;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
