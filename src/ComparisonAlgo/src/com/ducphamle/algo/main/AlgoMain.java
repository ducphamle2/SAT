/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComparisonAlgo.src.com.ducphamle.algo.main;

import ComparisonAlgo.src.com.ducphamle.algo.view.AlgoView;

/**
 *
 * @author Duc Pham Le
 */
public class AlgoMain {
    public AlgoMain() {
        init();
    }
    
    public void init() {
        AlgoView algoView = new AlgoView();
        algoView.run();
    }
    
    public static void main(String[] args) {
        new AlgoMain();
    }
}
