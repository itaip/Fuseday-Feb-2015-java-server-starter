var app = angular.module('fuse-team1.directives');
app.directive('mapDisplay', ['$interval', 'attacksService', function($interval, attacksService) {
    // Runs during compile
    return {
        // name: '',
        // priority: 1,
        // terminal: true,
        // scope: {}, // {} = isolate, true = child, false/undefined = no change
        // controller: function($scope, $element, $attrs, $transclude) {},
        // require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
        // restrict: 'A', // E = Element, A = Attribute, C = Class, M = Comment
        // template: '',
        templateUrl: '/views/map.tmpl.html',
        // replace: true,
        // transclude: true,
        // compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
        link: linkFunc
    };

    function linkFunc($scope, elem) {
        var map = new Datamap({
            element: document.getElementById('container'),
            fills: {
                defaultFill: '#000',
                blue: 'red'
            },
            geographyConfig: {
                highlightOnHover: true,
                popupOnHover: false,
                borderColor: '#63233d',
                highlightFillColor: '#e45785',
                borderWidth: 1
            }
        });

        $interval(function() {
            attacksService.getAttacks().then(function(result) {
                debugger;
                // map.bubbles([{
                //     name: 'aaa',
                //     radius: 10,
                //     latitude: lats[Math.floor(Math.random() * 4)],
                //     longitude: longs[Math.floor(Math.random() * 4)],
                //     fillKey: 'blue'
                // }])
            });

        }, 200);
    }
}]);
