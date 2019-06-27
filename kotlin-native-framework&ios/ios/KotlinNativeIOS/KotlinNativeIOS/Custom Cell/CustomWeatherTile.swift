//
//  CustomWeatherTile.swift
//  KotlinNativeIOS
//
//  Created by Avinash Mandal on 24/5/19.
//  Copyright © 2019 Avinash Mandal. All rights reserved.
//

import UIKit
import KotlinShared

class CustomWeatherTile: UITableViewCell {
    
    @IBOutlet weak var icon: UIImageView!
    @IBOutlet weak var temeperature: UILabel!
    @IBOutlet weak var condition: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    override public init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    public func bind(data: DailyData) {
        if let minTemp = data.temperatureMin as? Double,
            let maxTemp = data.temperatureMax as? Double {
            temeperature.text = "Min: \(dependencies().dataConverter.getCelcius(fahrenheit: minTemp) )°C / Max: \(dependencies().dataConverter.getCelcius(fahrenheit: maxTemp))°C"
        }
        
        if let summary = data.summary,
           let time = data.time as? Int64 {
            let dateString = [String(describing: IosActualKt.getDate(time: time).get(index: 0)!),
                              String(describing: IosActualKt.getDate(time: time).get(index: 1)!),
                              String(describing: IosActualKt.getDate(time: time).get(index: 2)!)]
                .compactMap{$0}.joined(separator: " ")
            let summaryString = [dateString, summary].joined(separator: "\n")
            condition.text = summaryString
        }
        
        if let image = UIImage(named: dependencies().dataConverter.getIcon(type: data.icon)) {
            icon.image = image
        }
    }
}
