
package com.px.finallywork.entity.main_home;

import java.util.List;
import lombok.Data;

@Data
public class Result{

    private List<ActInfo> actInfo;
    private List<BannerInfo> bannerInfo;
    private List<ChannelInfo> channelInfo;
    private List<HotInfo> hotInfo;
    private List<RecommendInfo> recommendInfo;
    private SeckillInfo seckillInfo;

}
