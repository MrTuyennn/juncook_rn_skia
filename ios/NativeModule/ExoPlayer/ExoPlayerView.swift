//
//  ExoPlayerView.swift
//  juncook_rn_skia
//
//  Created by Nguyễn Ngọc Tuyên on 17/08/2023.
//

import Foundation


class ExoPlayerView : UIView {
  @objc var onDidScanCard: RCTDirectEventBlock?

  @objc var count = 0 {
      didSet {
        button.setTitle(String(describing: count), for: .normal)
      }
    }
  
  
    override init(frame: CGRect) {
      super.init(frame: frame)
      self.addSubview(button)
//      increment()
    }
    required init?(coder aDecoder: NSCoder) {
      fatalError("init(coder:) has not been implemented")
    }
    lazy var button: UIButton = {
      let b = UIButton.init(type: UIButton.ButtonType.system)
      b.titleLabel?.font = UIFont.systemFont(ofSize: 50)
      b.autoresizingMask = [.flexibleWidth, .flexibleHeight]
      b.addTarget(
        self,
        action: #selector(increment),
        for: .touchUpInside
      )
     
      return b
    }()

  
  
    @objc func incrementExo() {
      count = count + 1
    }
  
  @objc func increment(){
    let cardData: [AnyHashable: Any] = [
                    "cardNumber":  "1342000",
                    "expiryMonth":  "04",
                    "expiryYear":  "2000",
                    "holderName":  "Nguyen Ngoc Tuyen"
                ]
    onDidScanCard!(cardData)
  }
  
}
