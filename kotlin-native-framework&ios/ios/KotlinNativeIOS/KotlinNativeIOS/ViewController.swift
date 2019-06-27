//
//  ViewController.swift
//  KotlinNativeIOS
//
//  Created by Avinash Mandal on 17/5/19.
//  Copyright © 2019 Avinash Mandal. All rights reserved.
//

import UIKit
import KotlinShared

class ViewController: UIViewController {
    
    @IBOutlet weak var weatherListView: UITableView!
   
    private lazy var presenter: WeatherPresenter = {
        dependencies().weatherPresenter()
    }()
    
    private lazy var activityIndicator: UIActivityIndicatorView = {
        let activityIndicator = UIActivityIndicatorView(frame: .zero)
        activityIndicator.style = .gray
        return activityIndicator
    }()
    
    private var weatherDataList = [DailyData]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        configureActivityIndicator()
        
        configureWeatherList()
        CommonKt.sayHelloWorld()
        
        presenter.bind(view: self)
        presenter.loadDailyData(lat: -33.8705036, lng: 151.1947947)
    }
    
    func configureActivityIndicator() {
        title = "Weather App"
        let rightBarButton = UIBarButtonItem(customView: activityIndicator)
        navigationItem.setRightBarButton(rightBarButton, animated: true)
    }
    
    func configureWeatherList() {
        weatherListView.dataSource = self
        let nib = UINib(nibName: "CustomWeatherTile", bundle: nil)
        weatherListView.register(nib, forCellReuseIdentifier: "customWeatherCell")
        weatherListView.rowHeight = UITableView.automaticDimension
        weatherListView.estimatedRowHeight = 120
        weatherListView.separatorStyle = .none
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        presenter.unbind()
        super.viewWillDisappear(animated)
    }
}

extension ViewController: WeatherView {
    func showWeatherData(dataList: [DailyData]) {
        weatherDataList = dataList
        weatherListView.separatorStyle = .singleLine
        weatherListView.reloadData()
    }
    
    func showProgressBar() {
        activityIndicator.startAnimating()
    }
    
    func hideProgressBar() {
        activityIndicator.stopAnimating()
    }
    
    func showErrorMessage(errorMessage: String?) {
        if let errorMessage = errorMessage {
            let alert = UIAlertController(title: "Alert", message: errorMessage, preferredStyle: .alert)
            let alertAction = UIAlertAction(title: "OK", style: .default)
            alert.addAction(alertAction)
            present(alert, animated: true, completion: nil)
        }
    }
}

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return weatherDataList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let dailyData = weatherDataList[indexPath.row]
        
        if let cell = tableView.dequeueReusableCell(withIdentifier: "customWeatherCell", for: indexPath) as? CustomWeatherTile {
            cell.bind(data: dailyData)
            return cell
        } else {
            let  cell = CustomWeatherTile(style: .default,
                                          reuseIdentifier: "customWeatherCell")
            cell.bind(data: dailyData)
            return cell
        }
    }
}

