//
//  ExoPlayer.m
//  juncook_rn_skia
//
//  Created by Nguyễn Ngọc Tuyên on 17/08/2023.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTViewManager.h>


@interface RCT_EXTERN_MODULE(ExoPlayerManager,RCTViewManager)
RCT_EXPORT_VIEW_PROPERTY(count, NSNumber)
RCT_EXPORT_VIEW_PROPERTY(onDidScanCard, RCTDirectEventBlock)
@end
