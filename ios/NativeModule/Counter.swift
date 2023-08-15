//
//  Counter.swift
//  juncook_rn_skia
//
//  Created by Nguyễn Ngọc Tuyên on 14/08/2023.
//

import Foundation

@objc(Counter)
class Counter: NSObject {
  private var count = 0
  
  @objc
  func increment(_ callback:RCTResponseSenderBlock){
    count += 1
//    print(count)
    callback([count])
  }
  
  @objc
  func decrement(_ resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock)
  {
    if(count == 0){
      let error = NSError(domain:"",code:200,userInfo: nil)
      reject("ERROR_COUNT","count cannot be negative",error)
    } else {
      count -= 1
      resolve(count)
    }
  }
  


    
  
  @objc
    static func requiresMainQueueSetup() ->Bool{
      return true;
  }
  
  
}
