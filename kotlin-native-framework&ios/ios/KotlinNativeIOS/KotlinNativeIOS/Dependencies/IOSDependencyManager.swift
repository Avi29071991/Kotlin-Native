//
//  DependencyManager.swift
//  KotlinNativeIOS
//
//  Created by Avinash Mandal on 23/5/19.
//  Copyright © 2019 Avinash Mandal. All rights reserved.
//

import Foundation
import KotlinShared

class IOSDependencyManager: NSObject {
    
    private lazy var uiContext : KotlinCoroutineContext = {
        MainDispatcher()
    }()
    
    private lazy var weatherRepository: WeatherRepository = {
        let api = WeatherApi()
        return WeatherRepositoryImpl(api: api)
    }()
    
    func weatherPresenter() -> WeatherPresenter {
        return WeatherPresenter(uiContext: uiContext,
                                repository: weatherRepository)
    }
    
    lazy var dataConverter: WeatherDataConverter = {
        WeatherDataConverter()
    }()
}
