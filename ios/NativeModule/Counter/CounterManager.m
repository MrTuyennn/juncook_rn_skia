//
//  CounterManager.m
//  juncook_rn_skia
//
//  Created by Nguyễn Ngọc Tuyên on 14/08/2023.
//

#import <Foundation/Foundation.h>
#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"

@interface RCT_EXTERN_MODULE(Counter,RCTEventEmitter)

RCT_EXTERN_METHOD(increment:(RCTResponseSenderBlock)callback)
RCT_EXTERN_METHOD(decrement:(RCTPromiseResolveBlock) resolve
                  reject:(RCTPromiseRejectBlock)reject)

@end
