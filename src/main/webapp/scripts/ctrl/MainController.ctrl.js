/**
 * Created by robiferentz on 2/24/15.
 */
angular.module('fuse-team1.controllers')
    .controller('MainController', ['$scope', '$http', function ($scope, $http) {
        "use strict";

        $scope.mapMsg = 'Hi';

        $scope.attack = function(){
            $http.post('/attack/' + $scope.attackIP + '/10').success(function(res){
                console.log('Attack started.');
            });
        };

        $scope.stop = function(){
            $http.delete('/attack').success(function(){

            });
        };
    }]);