var app = angular.module('fuse-team1.directives');
app.directive('mapDisplay', [function() {
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
        link: function($scope, iElm, iAttrs, controller) {
            var map = new Datamap({
                element: document.getElementById('container')
            });
            map.bubbles([{
                name: 'Castle Bravo',
                radius: 25,
                yeild: 15000,
                country: 'USA',
                significance: 'First dry fusion fuel "staged" thermonuclear weapon; a serious nuclear fallout accident occurred',
                fillKey: 'USA',
                date: '1954-03-01',
                latitude: 11.415,
                longitude: 165.1619
            }, {
                name: 'Tsar Bomba',
                radius: 70,
                yeild: 50000,
                country: 'USSR',
                fillKey: 'RUS',
                significance: 'Largest thermonuclear weapon ever tested—scaled down from its initial 100 Mt design by 50%',
                date: '1961-10-31',
                latitude: 73.482,
                longitude: 54.5854
            }]);

        }
    };
}]);
