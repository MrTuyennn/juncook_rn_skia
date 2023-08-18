//
//  ExoPlayer.swift
//  juncook_rn_skia
//
//  Created by Nguyễn Ngọc Tuyên on 17/08/2023.
//

import Foundation

@objc(ExoPlayerManager)
class ExoPlayerManager: RCTViewManager {
  
  override func view() -> UIView! {
    return ExoPlayerView()
  }
  
  override static func requiresMainQueueSetup() -> Bool {
          return true
  }
}
