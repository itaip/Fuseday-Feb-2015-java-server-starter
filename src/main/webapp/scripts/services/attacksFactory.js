angular.module('fuse-team1.services').factory("attacksService", ['$http', function($http) {

    return {
        getAttacks: function() {
            return $http({
                method: 'GET',
                url: '/attacks'
            });
        }
    };
}]);
